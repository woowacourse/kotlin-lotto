package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test

class RandomNumberGeneratorTest {
    @Test
    fun `범위와 크기를 입력받으면 주어진 크기의 범위에 속하는 숫자만을 포함한 오름차순으로 정렬된 리스트를 반환한다`() {
        val range = 1..10
        val size = 5

        val result = RandomNumberGenerator.generate(range, size)

        assertAll(
            { assertThat(result.size).isEqualTo(size) },
            { assertThat(result).allMatch { it in range } },
            { assertThat(result).isEqualTo(result.sorted()) },
        )
    }
}
