package cn.zuster.sse.controller;

import cn.zuster.sse.service.SseService;
import com.sun.tools.javac.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * SSE测试控制器
 *
 * @author songyh
 * @date 2021/1/5
 */
@RestController
@RequestMapping("/sse/steram")
public class SseStreamTestController {
    private static final Logger logger = LoggerFactory.getLogger(SseStreamTestController.class);


    // 推送当前时间，每秒推送一次
    @GetMapping(value = "/streamTime", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamTime() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "Current Time: " + LocalTime.now());
    }


    // 动态查询与推送数据示例
    @GetMapping(value = "/streamMessages", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamMessages() {
        Stream<String> messages = Stream.of("Hello", "World", "From", "SSE");
        return Flux.fromStream(messages)
                .delayElements(Duration.ofSeconds(1))  // 模拟延迟
                .map(msg -> "Message: " + msg);
    }


    @GetMapping(value = "/sse/complete", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamAndComplete() {
        return Flux.just("Message 1", "Message 2", "Goodbye")
                .delayElements(Duration.ofSeconds(1))
                .concatWith(Flux.empty());  // 主动结束连接
    }


    // 模拟数据库异步查询
    private CompletableFuture<List<String>> mockDatabaseQuery(int batch) {
        return CompletableFuture.supplyAsync(() -> List.of(
                "Data Batch " + batch + "-1",
                "Data Batch " + batch + "-2",
                "Data Batch " + batch + "-3"
        ));
    }

    // 边查边推示例-01
    @GetMapping(value = "/streamData", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamData() {
        return Flux.create(sink -> pushDataRecursively(sink, 1));
    }

    private void pushDataRecursively(FluxSink<String> sink, int batch) {
        if (batch > 5) {
            sink.complete();  // 完成推送
            return;
        }
        mockDatabaseQuery(batch).thenAccept(data -> {
            data.forEach(sink::next);  // 推送当前批次的数据
            // 推送下一批数据
            pushDataRecursively(sink, batch + 1);
        }).exceptionally(ex -> {
            sink.error(ex);  // 处理异常
            return null;
        });
    }


    // 模拟数据库查询
    private Mono<List<String>> queryDatabase(int batch) {
        // 假设我们按时间分段查询数据，每次查询 3 条
        return Mono.just(List.of(
                "Data Batch " + batch + "-1",
                "Data Batch " + batch + "-2",
                "Data Batch " + batch + "-3"
        ));
    }

    // 按时间分段查询数据并推送给前端
    @GetMapping(value = "/sse/data", produces = "text/event-stream")
    public Flux<Object> sseData() {
        return Flux.create(sink -> {
            for (int i = 1; i <= 5; i++) {
                int batch = i;
                // 异步查询数据库数据
                queryDatabase(batch)
                        .doOnTerminate(() -> {
                            if (batch == 5) {
                                sink.complete();  // 完成推送
                            }
                        })
                        .subscribe(data -> {
                            // 将查询到的数据推送到前端
                            data.forEach(sink::next);
                        });
            }
        }).delayElements(Duration.ofSeconds(1));  // 模拟数据查询的间隔
    }

}
