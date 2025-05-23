package org.usvm.spring.benchmarks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.junit.Assert;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.usvm.spring.benchmarks.model.GraphNode;
import org.usvm.spring.benchmarks.model.OurPoint;
import org.usvm.spring.benchmarks.model.TestJsonPayload;
import org.usvm.spring.benchmarks.model.TestJsonPayloadWithCtor;
import org.usvm.spring.benchmarks.model.Wallet;
import org.usvm.spring.benchmarks.repository.GraphRepository;
import org.usvm.spring.benchmarks.service.GraphService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.mockito.internal.matchers.text.ValuePrinter.print;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@DisabledInAotMode
@ExtendWith({SpringExtension.class})
@WebMvcTest()
public class SampleTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GraphRepository repository;

    @MockBean
    private GraphService service;

    @Test
    public void linkNodeTest() throws Exception {
        Mockito.when(repository.getNodeById(Mockito.anyString())).thenReturn(new GraphNode(228, null, null));
        Mockito.when(service.linkNode(Mockito.any())).thenReturn(new GraphNode(229, null, null));
        ResultActions resultActions = mockMvc.perform(get("/service/link_node").param("node_id", "32"));
    }

    @Test
    public void testThrowsException() throws Exception {
//        MvcResult resultActions = mockMvc.perform(get("/exception/throw").param("param", "32")).andReturn();
//        resultActions.getResolvedException().getMessage();
        ThrowingRunnable r = () -> print("123");
        assertThrows(NullPointerException.class, r);
    }

    @Test
    public void notRequiredTest() throws Exception {
        MvcResult resultActions = mockMvc.perform(get("/simple/notRequired")).andReturn();
        resultActions.getResolvedException().getMessage();
        ThrowingRunnable r = () -> print("123");
        assertThrows(NullPointerException.class, r);
    }

    @Test
    void bodyObject() throws InstantiationException, Exception, JsonProcessingException {
        TestJsonPayload v = ReflectionUtils.<TestJsonPayload>allocateInstance(TestJsonPayload.class);
        v.intValue = 0;
        int v1 = 0;
        v.integerValue = v1;
        byte[] v2 = new byte[10];
        v2[0] = (byte) 0;
        v2[1] = (byte) 0;
        v2[2] = (byte) 0;
        v2[3] = (byte) 0;
        v2[4] = (byte) 0;
        v2[5] = (byte) 0;
        v2[6] = (byte) 0;
        v2[7] = (byte) 0;
        v2[8] = (byte) 0;
        v2[9] = (byte) 0;
        String v3 = new String(v2);
        v.stringValue = v3;
        OurPoint v4 = ReflectionUtils.<OurPoint>allocateInstance(OurPoint.class);
        ReflectionTestUtils.setField(v4, "x", 0);
        ReflectionTestUtils.setField(v4, "y", 0);
        new Object();
        v.objectValue = v4;
        GraphNode v5 = ReflectionUtils.<GraphNode>allocateInstance(GraphNode.class);
        v5.value = 0;
        GraphNode v6 = ReflectionUtils.<GraphNode>allocateInstance(GraphNode.class);
        v6.value = 0;
        GraphNode v7 = ReflectionUtils.<GraphNode>allocateInstance(GraphNode.class);
        v7.value = 0;
        v7.left = null;
        v7.right = null;
        new Object();
        v6.left = v7;
        GraphNode v8 = ReflectionUtils.<GraphNode>allocateInstance(GraphNode.class);
        v8.value = 0;
        v8.left = null;
        v8.right = null;
        new Object();
        v6.right = v8;
        new Object();
        v5.left = v6;
        GraphNode v9 = ReflectionUtils.<GraphNode>allocateInstance(GraphNode.class);
        v9.value = 0;
        GraphNode v10 = ReflectionUtils.<GraphNode>allocateInstance(GraphNode.class);
        v10.value = 0;
        v10.left = null;
        v10.right = null;
        new Object();
        v9.left = v10;
        GraphNode v11 = ReflectionUtils.<GraphNode>allocateInstance(GraphNode.class);
        v11.value = 0;
        v11.left = null;
        v11.right = null;
        new Object();
        v9.right = v11;
        new Object();
        v5.right = v9;
        new Object();
        v.recursiveObjectValue = v5;
        v.ignoredProperty = 0;
        v.includedProperty = 0;
        TestJsonPayloadWithCtor v12 = ReflectionUtils.<TestJsonPayloadWithCtor>allocateInstance(TestJsonPayloadWithCtor.class);
        v12.first = 13;
        v12.second = 33;
        new Object();
        v.beanWithCtor = v12;
        ArrayList<String> v13 = new ArrayList<>();
        byte[] v14 = new byte[1];
        v14[0] = (byte) 0;
        String v15 = new String(v14);
        v13.add(v15);
        v13.add(v15);
        v13.add(v15);
        v.stringList = v13;
        ArrayList<List<String>> v16 = new ArrayList<>();
        ArrayList<String> v17 = new ArrayList<>();
        String v18 = new String(v14);
        v17.add(v18);
        v17.add(v18);
        v17.add(v18);
        v16.add(v17);
        v16.add(v17);
        v16.add(v17);
        v.stringListList = v16;
        String v19 = new String(new byte[0]);
        String[] v20 = new String[3];
        v20[0] = v19;
        v20[1] = v19;
        v20[2] = v19;
        v.stringArray = v20;
        String v21 = new String(v14);
        String[] v22 = new String[3];
        v22[0] = v21;
        v22[1] = v21;
        v22[2] = v21;
        String[][] v23 = new String[3][3];
        v23[0] = v22;
        String v24 = new String(new byte[0]);
        String[] v25 = new String[3];
        v25[0] = v24;
        v25[1] = v24;
        v25[2] = v24;
        v23[1] = v25;
        String v26 = new String(v14);
        String[] v27 = new String[3];
        v27[0] = v26;
        v27[1] = v26;
        v27[2] = v26;
        v23[2] = v27;
        v.stringArrayArray = v23;
        ArrayList<TestJsonPayloadWithCtor> v28 = new ArrayList<>();
        TestJsonPayloadWithCtor v29 = ReflectionUtils.allocateInstance(TestJsonPayloadWithCtor.class);
        v29.first = 0;
        v29.second = 0;
        new Object();
        v28.add(v29);
        v28.add(v29);
        v28.add(v29);
        v.objectList = v28;
        ArrayList<List<TestJsonPayloadWithCtor>> v30 = new ArrayList<>();
        ArrayList<TestJsonPayloadWithCtor> v31 = new ArrayList<>();
        TestJsonPayloadWithCtor v32 = ReflectionUtils.<TestJsonPayloadWithCtor>allocateInstance(TestJsonPayloadWithCtor.class);
        v32.first = 0;
        v32.second = 0;
        new Object();
        v31.add(v32);
        v31.add(v32);
        v31.add(v32);
        v30.add(v31);
        v30.add(v31);
        v30.add(v31);
        v.objectListList = v30;
        TestJsonPayloadWithCtor v33 = ReflectionUtils.<TestJsonPayloadWithCtor>allocateInstance(TestJsonPayloadWithCtor.class);
        v33.first = 0;
        v33.second = 0;
        new Object();
        TestJsonPayloadWithCtor[] v34 = new TestJsonPayloadWithCtor[3];
        v34[0] = v33;
        TestJsonPayloadWithCtor v35 = ReflectionUtils.<TestJsonPayloadWithCtor>allocateInstance(TestJsonPayloadWithCtor.class);
        v35.first = 0;
        v35.second = 0;
        new Object();
        v34[1] = v35;
        TestJsonPayloadWithCtor v36 = ReflectionUtils.<TestJsonPayloadWithCtor>allocateInstance(TestJsonPayloadWithCtor.class);
        v36.first = 0;
        v36.second = 0;
        new Object();
        v34[2] = v36;
        v.objectArray = v34;
        TestJsonPayloadWithCtor v37 = ReflectionUtils.<TestJsonPayloadWithCtor>allocateInstance(TestJsonPayloadWithCtor.class);
        v37.first = 0;
        v37.second = 0;
        new Object();
        TestJsonPayloadWithCtor[] v38 = new TestJsonPayloadWithCtor[3];
        v38[0] = v37;
        TestJsonPayloadWithCtor v39 = ReflectionUtils.<TestJsonPayloadWithCtor>allocateInstance(TestJsonPayloadWithCtor.class);
        v39.first = 0;
        v39.second = 0;
        new Object();
        v38[1] = v39;
        TestJsonPayloadWithCtor v40 = ReflectionUtils.<TestJsonPayloadWithCtor>allocateInstance(TestJsonPayloadWithCtor.class);
        v40.first = 0;
        v40.second = 0;
        new Object();
        v38[2] = v40;
        TestJsonPayloadWithCtor[][] v41 = new TestJsonPayloadWithCtor[3][3];
        v41[0] = v38;
        TestJsonPayloadWithCtor v42 = ReflectionUtils.<TestJsonPayloadWithCtor>allocateInstance(TestJsonPayloadWithCtor.class);
        v42.first = 0;
        v42.second = 0;
        new Object();
        TestJsonPayloadWithCtor[] v43 = new TestJsonPayloadWithCtor[3];
        v43[0] = v42;
        TestJsonPayloadWithCtor v44 = ReflectionUtils.<TestJsonPayloadWithCtor>allocateInstance(TestJsonPayloadWithCtor.class);
        v44.first = 0;
        v44.second = 0;
        new Object();
        v43[1] = v44;
        TestJsonPayloadWithCtor v45 = ReflectionUtils.<TestJsonPayloadWithCtor>allocateInstance(TestJsonPayloadWithCtor.class);
        v45.first = 0;
        v45.second = 0;
        new Object();
        v43[2] = v45;
        v41[1] = v43;
        TestJsonPayloadWithCtor v46 = ReflectionUtils.<TestJsonPayloadWithCtor>allocateInstance(TestJsonPayloadWithCtor.class);
        v46.first = 0;
        v46.second = 0;
        new Object();
        TestJsonPayloadWithCtor[] v47 = new TestJsonPayloadWithCtor[3];
        v47[0] = v46;
        TestJsonPayloadWithCtor v48 = ReflectionUtils.<TestJsonPayloadWithCtor>allocateInstance(TestJsonPayloadWithCtor.class);
        v48.first = 0;
        v48.second = 0;
        new Object();
        v47[1] = v48;
        TestJsonPayloadWithCtor v49 = ReflectionUtils.<TestJsonPayloadWithCtor>allocateInstance(TestJsonPayloadWithCtor.class);
        v49.first = 0;
        v49.second = 0;
        new Object();
        v47[2] = v49;
        v41[2] = v47;
        v.objectArrayArray = v41;
        new Object();
        Integer v50 = 200;
        String c = new ObjectMapper().writeValueAsString(v);
        Object result = this.mockMvc.perform(MockMvcRequestBuilders.post("/body/object").contentType(MediaType.APPLICATION_JSON).content(c));
    }

    @Test
    void bodyWithValidation() throws InstantiationException, Exception, JsonProcessingException {
        Wallet v = ReflectionUtils.<Wallet>allocateInstance(Wallet.class);
        ReflectionTestUtils.setField(v, "cash", "22");
        ReflectionTestUtils.setField(v, "cards", null);
        ReflectionTestUtils.setField(v, "card", null);
        Integer v1 = Integer.valueOf(0);
        ReflectionTestUtils.setField(v, "id", v1);
        Assertions.assertThrows(ServletException.class, () -> this.mockMvc.perform(MockMvcRequestBuilders.get("/body/body_with_validation").content(new ObjectMapper().writeValueAsString(v)).contentType(MediaType.APPLICATION_JSON)));
    }


    @Test
    void httpEntity() throws InstantiationException, Exception, JsonProcessingException {
        Wallet v = ReflectionUtils.allocateInstance(Wallet.class);
        ReflectionTestUtils.setField(v, "cash", "11");
        ReflectionTestUtils.setField(v, "cards", null);
        ReflectionTestUtils.setField(v, "card", null);
        ReflectionTestUtils.setField(v, "id", 1);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/other/http_entity").content(new ObjectMapper().writeValueAsString(v)).contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void incrementBody() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/simple/increment_from_body").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(content().string("1"));
    }

    @Test
    void walletParam() throws Exception {
        mockMvc.perform(get("/complex/wallet_header/")
                .param("wallet[cash]", "123")
                .param("wallet[id]", "228337")
        );
    }

    @Test
    void walletHeader() throws Exception {
        mockMvc.perform(get("/complex/wallet_header/")
                .header("wallet", "{\"cash\": \"123\"}")
        );
    }

    @Test
    void matrixStuff() throws Exception {
        mockMvc.perform(get("/complex/matrix_map/{something};x=123;y=234", "something")).andExpect(content().string("123234"));
    }


    @Test
    void walletMoneyFromParam() throws Exception {
        String[] v = new String[1];
        v[0] = "0000-00-00";
        String[] v1 = new String[1];
        v1[0] = "1";
        String[] v2 = new String[1];
        v2[0] = "+00";
        String[] v3 = new String[1];
        v3[0] = "0000-00-00";
        String[] v4 = new String[1];
        v4[0] = "1";
        String[] v5 = new String[1];
        v5[0] = "0000-00-00";
        String[] v6 = new String[1];
        v6[0] = "1";
        String[] v7 = new String[1];
        v7[0] = "0000-00-00";
        String[] v8 = new String[1];
        v8[0] = "1";
        String[] v9 = new String[1];
        v9[0] = "0000-00-00";
        String[] v10 = new String[1];
        v10[0] = "1";
        String[] v11 = new String[1];
        v11[0] = "0000-00-00";
        String[] v12 = new String[1];
        v12[0] = "1";
        String[] v13 = new String[1];
        v13[0] = "-8";
        Integer v14 = Integer.valueOf(200);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/complex/wallet_param/")
                        .param("card.expiryDate", v)
                        .param("card.id", v1)
                        .param("cash", v2)
                        .param("cards[0].expiryDate", v3)
                        .param("cards[0].id", v4)
                        .param("cards[1].expiryDate", v5)
                        .param("cards[1].id", v6)
                        .param("cards[2].expiryDate", v7)
                        .param("cards[2].id", v8)
                        .param("cards[3].expiryDate", v9)
                        .param("cards[3].id", v10)
                        .param("cards[4].expiryDate", v11)
                        .param("cards[4].id", v12)
                        .param("id", v13))
                .andExpect(MockMvcResultMatchers.status().is(v14)).andExpect(content().string("-1"));
    }
}
