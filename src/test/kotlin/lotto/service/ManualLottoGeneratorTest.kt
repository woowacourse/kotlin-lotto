package lotto.service

import lotto.domain.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ManualLottoGeneratorTest {
    @Test
    fun `주어진 숫자 리스트로 LottoNumber 리스트를 생성한다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val generatedNumbers = ManualLottoGenerator(numbers).generate()

        assertThat(generatedNumbers)
            .hasSize(numbers.size)
            .allMatch { it is LottoNumber }
    }
}
