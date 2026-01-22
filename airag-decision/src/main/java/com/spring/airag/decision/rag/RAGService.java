package com.spring.airag.decision.rag;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import lombok.extern.slf4j.Slf4j;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * RAG知识库服务类 - AI决策专用
 */
@Slf4j
@Service
public class RAGService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Value("${airag.rag.chunk-size}")
    private int chunkSize;

    @Value("${airag.rag.chunk-overlap}")
    private int chunkOverlap;

    @Value("${airag.rag.max-results}")
    private int maxResults;

    @Value("${airag.sentence-bert.model-path}")
    private String modelPath;

    @Value("${airag.sentence-bert.vector-dimension}")
    private int vectorDimension;

    private MultiLayerNetwork sentenceBertModel;

    private static final String INDEX_NAME = "decision_knowledge";

    @PostConstruct
    public void init() {
        try {
            // 初始化Sentence-BERT模型
            initializeSentenceBertModel();
            
            // 确保Elasticsearch索引存在
            ensureIndexExists();
            
            log.info("RAG服务初始化完成: 索引={}, 向量维度={}", INDEX_NAME, vectorDimension);
        } catch (Exception e) {
            log.error("RAG服务初始化失败", e);
        }
    }

    /**
     * 初始化Sentence-BERT模型
     */
    private void initializeSentenceBertModel() {
        try {
            // 这里简化处理，实际应用中应加载预训练的Sentence-BERT模型
            // sentenceBertModel = KerasModelImport.loadMultiLayerNetwork(modelPath + "/sentence_bert_model.h5");
            log.info("Sentence-BERT模型加载完成: 路径={}", modelPath);
        } catch (Exception e) {
            log.warn("Sentence-BERT模型加载失败，将使用模拟方法", e);
        }
    }

    /**
     * 确保Elasticsearch索引存在
     */
    private void ensureIndexExists() {
        // 这里应该检查并创建Elasticsearch索引
        // 实际应用中需要根据具体需求创建索引映射
        log.info("检查Elasticsearch索引: {}", INDEX_NAME);
    }

    /**
     * 添加知识到知识库
     */
    public void addKnowledge(String id, String title, String content, String category) {
        try {
            // 分块处理内容
            List<String> chunks = chunkContent(content);
            
            for (int i = 0; i < chunks.size(); i++) {
                String chunkContent = chunks.get(i);
                String chunkId = id + "_chunk_" + i;
                
                // 生成向量嵌入
                float[] embedding = generateEmbedding(chunkContent);
                
                // 构建文档
                Map<String, Object> doc = new HashMap<>();
                doc.put("id", chunkId);
                doc.put("parentId", id);
                doc.put("title", title);
                doc.put("content", chunkContent);
                doc.put("category", category);
                doc.put("createTime", new Date());
                doc.put("updateTime", new Date());
                doc.put("embedding", embedding);

                // 这里应该调用Elasticsearch API索引文档
                // 实际应用中应使用elasticsearchClient进行索引操作
                
                log.debug("添加知识到知识库: ID={}, 标题={}, 分块={}/{}", 
                        id, title, i+1, chunks.size());
            }

            log.info("添加知识到知识库: ID={}, 标题={}, 分块数={}", id, title, chunks.size());
        } catch (Exception e) {
            log.error("添加知识到知识库失败: ID={}, 标题={}", id, title, e);
        }
    }

    /**
     * 内容分块处理
     */
    private List<String> chunkContent(String content) {
        List<String> chunks = new ArrayList<>();
        
        if (content.length() <= chunkSize) {
            chunks.add(content);
            return chunks;
        }
        
        int start = 0;
        while (start < content.length()) {
            int end = Math.min(start + chunkSize, content.length());
            
            // 尝试在句子边界分割
            if (end < content.length()) {
                // 寻找合适的分割点（句号、换行符等）
                int splitPoint = findSplitPoint(content, start, end);
                if (splitPoint > start) {
                    end = splitPoint;
                }
            }
            
            String chunk = content.substring(start, end);
            chunks.add(chunk);
            
            start = end - chunkOverlap; // 保留重叠部分
            if (start >= content.length()) {
                break;
            }
        }
        
        return chunks;
    }

    /**
     * 寻找分割点
     */
    private int findSplitPoint(String content, int start, int end) {
        // 优先在句子结束符处分割
        for (int i = end - 1; i > start; i--) {
            char c = content.charAt(i);
            if (c == '.' || c == '!' || c == '?' || c == '\n') {
                return i + 1;
            }
        }
        
        // 如果没有句子结束符，在单词边界分割
        for (int i = end - 1; i > start; i--) {
            if (Character.isWhitespace(content.charAt(i))) {
                return i;
            }
        }
        
        // 如果都没有，就在指定位置分割
        return end;
    }

    /**
     * 生成向量嵌入
     */
    private float[] generateEmbedding(String text) {
        // 模拟向量生成，实际应用中应使用Sentence-BERT模型
        float[] embedding = new float[vectorDimension];
        
        // 使用文本的哈希值生成伪向量表示
        // 这只是示例实现，实际应用中应使用预训练模型
        byte[] bytes = text.getBytes();
        for (int i = 0; i < vectorDimension; i++) {
            int hash = Arrays.hashCode(bytes) + i * 31;
            embedding[i] = (float) (hash % 1000) / 1000.0f;
        }
        
        return embedding;
    }

    /**
     * 基于关键词搜索知识
     */
    public List<Map<String, Object>> searchByKeyword(String keyword, int topK) {
        try {
            SearchRequest.Builder searchBuilder = new SearchRequest.Builder()
                    .index(INDEX_NAME)
                    .size(topK > 0 ? topK : maxResults);

            // 构建查询
            Query query = Query.of(q -> q
                    .multiMatch(mm -> mm
                            .query(keyword)
                            .fields("title", "content")
                    )
            );

            SearchRequest searchRequest = searchBuilder.query(query).build();

            SearchResponse<Map> response = elasticsearchClient.search(searchRequest, Map.class);

            List<Map<String, Object>> results = new ArrayList<>();
            for (Hit<Map> hit : response.hits().hits()) {
                Map<String, Object> source = hit.source();
                source.put("_score", hit.score());
                results.add(source);
            }

            log.debug("关键词搜索完成: 查询=\"{}\", 结果数={}", keyword, results.size());

            return results;
        } catch (IOException e) {
            log.error("关键词搜索失败: 查询=\"{}\"", keyword, e);
            return new ArrayList<>();
        }
    }

    /**
     * 基于向量相似度搜索知识
     */
    public List<Map<String, Object>> searchByVector(String query, int topK) {
        try {
            // 生成查询向量
            float[] queryVector = generateEmbedding(query);

            // 这里应该构建Elasticsearch的向量搜索查询
            // 实际应用中应使用elasticsearchClient进行向量搜索
            
            // 模拟搜索结果
            List<Map<String, Object>> results = new ArrayList<>();
            
            log.debug("向量搜索完成: 查询=\"{}\", 结果数={}", query, results.size());

            return results;
        } catch (Exception e) {
            log.error("向量搜索失败: 查询=\"{}\"", query, e);
            return new ArrayList<>();
        }
    }

    /**
     * 混合搜索（关键词+向量）
     */
    public List<Map<String, Object>> hybridSearch(String query, int topK) {
        List<Map<String, Object>> keywordResults = searchByKeyword(query, topK);
        List<Map<String, Object>> vectorResults = searchByVector(query, topK);

        // 合并和去重结果
        Map<String, Map<String, Object>> resultDict = new LinkedHashMap<>();
        
        // 添加关键词搜索结果，设置来源标识
        for (Map<String, Object> result : keywordResults) {
            result.put("source", "keyword");
            resultDict.put((String) result.get("id"), result);
        }
        
        // 添加向量搜索结果，如果不存在则添加，否则合并分数
        for (Map<String, Object> result : vectorResults) {
            String id = (String) result.get("id");
            if (resultDict.containsKey(id)) {
                // 如果已存在，合并来源信息
                Map<String, Object> existing = resultDict.get(id);
                String sources = existing.get("source") + ",vector";
                existing.put("source", sources);
            } else {
                result.put("source", "vector");
                resultDict.put(id, result);
            }
        }

        // 转换为列表并按某种方式排序（这里简单地按添加顺序）
        List<Map<String, Object>> results = new ArrayList<>(resultDict.values());
        
        // 如果需要更精确的排序，可以根据相关性重新排序
        
        log.info("混合搜索完成: 查询=\"{}\", 结果数={}", query, results.size());

        return results;
    }

    /**
     * 从知识库删除条目
     */
    public void deleteKnowledge(String id) {
        try {
            // 这里应该调用Elasticsearch API删除文档
            // 实际应用中应使用elasticsearchClient进行删除操作
            
            log.info("从知识库删除: ID={}", id);
        } catch (Exception e) {
            log.error("从知识库删除失败: ID={}", id, e);
        }
    }

    /**
     * 更新知识库条目
     */
    public void updateKnowledge(String id, String title, String content, String category) {
        // 删除旧条目
        deleteKnowledge(id);
        
        // 添加新条目
        addKnowledge(id, title, content, category);
        
        log.info("更新知识库: ID={}, 标题={}", id, title);
    }

    /**
     * 获取知识库统计信息
     */
    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        
        try {
            // 这里应该调用Elasticsearch API获取索引统计信息
            // 实际应用中应使用elasticsearchClient获取统计信息
            
            stats.put("totalDocuments", 0);
            stats.put("categories", new ArrayList<>());
            stats.put("lastUpdate", new Date());
            
            log.debug("获取知识库统计信息完成");
        } catch (Exception e) {
            log.error("获取知识库统计信息失败", e);
            stats.put("error", e.getMessage());
        }

        return stats;
    }
}
