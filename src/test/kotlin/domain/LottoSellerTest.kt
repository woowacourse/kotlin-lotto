package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoSellerTest {
    @Test
    fun `로또 한 장을 발급한다`() {
        val numberGenerator = TestLottoMachine(listOf(1, 2, 3, 4, 5, 6))
        val lottoSeller = LottoSeller(numberGenerator)
        val lotto = lottoSeller.sellLotto()
        val expected = listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }
        assertThat(lotto.numbers).containsAll(expected)
    }

    @Test
    fun `입력받은 개수만큼 로또를 발급한다`() {
        // given
        val numberGenerator = TestLottoMachine(listOf(1, 2, 3, 4, 5, 6))
        val lottoSeller = LottoSeller(numberGenerator)

        // when
        val actual = lottoSeller.sellLottos(2)

        // then
        assertThat(actual.size).isEqualTo(2)
    }

    inner class TestLottoMachine(private val numbers: List<Int>) : LottoMachine {
        override fun create(start: Int, end: Int): Lotto {
            return Lotto(numbers.map { number -> LottoNumber(number) }.toSet())
        }
    }
}
