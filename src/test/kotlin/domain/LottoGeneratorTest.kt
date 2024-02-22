package domain

import domain.model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGeneratorTest {
    @Test
    fun `로또 숫자 개수가 6개인지 확인한다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        assertThat(LottoGenerator.makeLotto(numbers).numbers.size).isEqualTo(6)
    }

    @Test
    fun `결과 로또가 오름차순인지 확인한다`() {
        val numbers = listOf(6, 5, 4, 3, 2, 1)
        assertThat(LottoGenerator.makeLotto(numbers).numbers).isEqualTo(
            (1..6).map { LottoNumber(it) }
        )
    }
}
