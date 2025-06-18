package com.alvaroeb.backend.infrastructure.controller.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class QueryParamsTest {

    @Test
    void defaultValuesShouldBeSet() {
        QueryParams params = new QueryParams();
        assertThat(params.getPage()).isEqualTo(0);
        assertThat(params.getSize()).isEqualTo(15);
        assertThat(params.getSearch()).isEqualTo("");
        assertThat(params.getSort()).isEqualTo("name");
        assertThat(params.getDirection()).isEqualTo("asc");
    }

    @Test
    void settersAndGettersShouldWork() {
        QueryParams params = new QueryParams();
        params.setPage(2);
        params.setSize(50);
        params.setSearch("Luke");
        params.setSort("height");
        params.setDirection("desc");

        assertThat(params.getPage()).isEqualTo(2);
        assertThat(params.getSize()).isEqualTo(50);
        assertThat(params.getSearch()).isEqualTo("Luke");
        assertThat(params.getSort()).isEqualTo("height");
        assertThat(params.getDirection()).isEqualTo("desc");
    }

    @Test
    void equalsAndHashCodeShouldWork() {
        QueryParams params1 = new QueryParams();
        QueryParams params2 = new QueryParams();

        assertThat(params1).isEqualTo(params2);
        assertThat(params1.hashCode()).isEqualTo(params2.hashCode());

        params2.setPage(1);
        assertThat(params1).isNotEqualTo(params2);
    }

    @Test
    void toStringShouldContainFieldValues() {
        QueryParams params = new QueryParams();
        String str = params.toString();
        assertThat(str).contains("page=0");
        assertThat(str).contains("size=15");
        assertThat(str).contains("search=");
        assertThat(str).contains("sort=name");
        assertThat(str).contains("direction=asc");
    }
}