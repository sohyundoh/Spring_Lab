package com.practice.lock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.lock.config.RedisConfig;
import com.practice.lock.controller.LockController;
import com.practice.lock.controller.Request;
import com.practice.lock.filter.CustomServletWrappingFilter;
import com.practice.lock.interceptor.LockInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LockTest {

    @Autowired
    private RedisConfig redisConfig;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    class TooManyRequest {
        @BeforeEach
        public void init() {
            mockMvc = MockMvcBuilders.standaloneSetup(new LockController())
                    .addFilter(new CustomServletWrappingFilter())
                    .build();
        }

        @Test
        @DisplayName("동시성 제어 테스트")
        public void tooManyRequest() throws Exception {
            int numberOfThread = 2;
            ExecutorService executorService = Executors.newFixedThreadPool(numberOfThread);
            CountDownLatch latch = new CountDownLatch(numberOfThread);
            for (int i = 2; i > 0; i--) {
                executorService.submit(
                        () -> {
                            try {
                                lockTest();
                                latch.countDown();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
            }
            latch.await();
        }

        private void lockTest() throws Exception {
            String request = objectMapper.writeValueAsString(new Request("이름"));

            mockMvc.perform(
                            post("/test")
                                    .content(request)
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(200))
                    .andDo(print());
            System.out.println("----------------");
        }
    }
}
