package org.allengueco.game;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;


@JsonTest
class GuessesJsonTest {
    @Autowired
    JacksonTester<Guesses> json;

    @Test
    void serializeGuesses() throws IOException {
        Guesses g = new Guesses();

        JsonContent<Guesses> content = json.write(g);

        assertThat(content)
                .extractingJsonPathArrayValue("$.before").isEmpty();

        assertThat(content)
                .extractingJsonPathArrayValue("$.after").isEmpty();
    }

    @Test
    void serializeContainsGuesses() throws IOException {
        Guesses g = new Guesses();

        g.setBefore(Set.of("base"));

        JsonContent<Guesses> content = json.write(g);

        assertThat(content)
                .extractingJsonPathArrayValue("$.before").contains("base");
    }

    @Test
    void deserializeGuesses() throws IOException {
        String g = """
                {
                    "before" : [],
                    "after" : []
                }
                """;

        ObjectContent<Guesses> content = json.parse(g);

        assertThat(content)
                .hasNoNullFieldsOrProperties();

        assertThat(content)
                .extracting(Guesses::getBefore, as(InstanceOfAssertFactories.LIST))
                .isEmpty();

        assertThat(content)
                .extracting(Guesses::getAfter, as(InstanceOfAssertFactories.LIST))
                .isEmpty();
    }

    @Test
    void deserializeContainsGuesses() throws IOException {
        String g = """
                {
                    "before" : ["mandarin", "power"],
                    "after" : ["apple", "boy"]
                }
                """;

        ObjectContent<Guesses> content = json.parse(g);

        assertThat(content)
                .hasNoNullFieldsOrProperties();

        assertThat(content)
                .extracting(Guesses::getAfter, as(InstanceOfAssertFactories.LIST))
                .containsExactlyElementsOf(List.of("apple", "boy"));

        assertThat(content)
                .extracting(Guesses::getBefore, as(InstanceOfAssertFactories.LIST))
                .containsExactlyElementsOf(List.of("mandarin", "power"));
    }
}