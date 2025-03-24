package org.usvm.spring.benchmarks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.usvm.spring.benchmarks.model.Card;
import org.usvm.spring.benchmarks.model.GraphNode;
import org.usvm.spring.benchmarks.model.Wallet;
import org.usvm.spring.benchmarks.service.CoolService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith({SpringExtension.class})
@WebMvcTest
@DisabledInAotMode
public class SimpleControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CoolService mockedService;

	@Test
	public void testUsername() throws Exception {
		var result = mockMvc.perform(get("/simple/increment_from_param/{name}", 123, 223).param("sampleParameter", "3212"));
		result.andExpect(header().stringValues("skibidi", "skibidi"));
	}


	@Test
	public void testMockService() throws Exception {
		GraphNode g = new GraphNode();
		g.value = 3241;
		GraphNode g2 = new GraphNode();
		g2.value = 32412;
		List<String> skib = new ArrayList<>();
		skib.add("123");
		Mockito.when(mockedService.returnList(Mockito.any())).thenReturn(skib).thenReturn(new ArrayList<>());
		var result = mockMvc.perform(get("/service/gen_wallet").param("sampleParameter", "3212"));
		result.andExpect(header().stringValues("skibidi", "skibidi"));
	}
}
