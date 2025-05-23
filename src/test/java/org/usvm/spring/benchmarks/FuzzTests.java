package org.usvm.spring.benchmarks;


import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.code_intelligence.jazzer.junit.FuzzTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.web.servlet.MockMvc;
import org.usvm.spring.benchmarks.repository.GraphRepository;
import org.usvm.spring.benchmarks.service.GraphService;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

@DisabledInAotMode
@WebMvcTest()
public class FuzzTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GraphRepository graphRepository;

    @MockBean
    private GraphService graphService;

    private String pathOfIndex(int index, FuzzedDataProvider data) {
        List<String> paths = Arrays.asList(
                "/body/body_with_validation",
                "/body/graph",
                "/body/object",
                "/body/list",
                "/body/array",
                "/complex/parameter_map",
                "/complex/header_map",
                "/complex/wallet_param/",
                "/exception/throw",
                "/exception/conditional",
                "/exception/status",
                "/other/http_entity",
                "/other/session_write",
                "/other/session_read",
                "/other/principal",
                "/other/authentication",
                "/service/link_node",
                "/simple/increment_from_param",
                "/simple/increment_from_body",
                "/simple/increment_from_form",
                "/simple/increment_from_header",
                "/simple/concat_stuff_from_request",
                "/simple/notRequired",
                "/wallets/addToModel",
                "/wallets/passThroughModel",
                "/wallets/readFromModel"
        );

        if (0 <= index && index < paths.size())
            return paths.get(index);

        if (index == paths.size()) {
            return "/complex/matrix_map/%s".formatted(
                    data.consumeString(20)
            );
        }

        if (index == paths.size() + 1) {
            return "/complex/path_map/%s/%s".formatted(
                    data.consumeString(20),
                    data.consumeString(20)
            );
        }

        return "/wallets/readFromModel";
    }

    private HttpMethod methodOfIndex(int index) {
        if (index == 0) {
            return HttpMethod.GET;
        }
        return HttpMethod.POST;
    }

    @FuzzTest()
    public void fuzzTest(FuzzedDataProvider data) throws Exception {
        int pathIndex = data.consumeInt(0, 26);
        HttpMethod method = methodOfIndex(data.consumeInt(0, 2));
        String path = pathOfIndex(pathIndex, data);
        mockMvc.perform(request(method, path));
    }
}
