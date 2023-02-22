package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGeneratorTest {
    @Test
    fun `자동 로또는 구입 금액만큼 로또를 발행한다`() {
        val actual = LottoGenerator.generateAuto(14).size
        assertThat(actual).isEqualTo(14)
    }

    @Test
    fun `수동 로또는 입력받은 번호로 로또를 발행한다`() {
        val lottoNumbers = listOf(1, 2, 3, 4, 5, 6)
        val actual = LottoGenerator.generateManual(lottoNumbers)
        val expected = makeLotto(listOf(1, 2, 3, 4, 5, 6))
        assertThat(actual).isEqualTo(expected)
    }

    private fun makeLotto(numbers: List<Int>): Lotto {
        return Lotto(numbers.map { LottoNumber.from(it) })
    }
}
