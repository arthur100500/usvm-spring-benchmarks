package org.usvm.spring.benchmarks.model;

public class NumberListDTO {
	private Long[] id;

	public Long[] getId() {
		return id;
	}

	public void setId(Long[] id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("NumberList with length: %d", id.length);
	}
}
