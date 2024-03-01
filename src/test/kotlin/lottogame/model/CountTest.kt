package lottogame.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CountTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, 0])
    fun `Count 는 0 보다 작으면 null 반환`(count: Int) {
        assertThat(Count.createOrNull(0)).isNull()
    }
}
