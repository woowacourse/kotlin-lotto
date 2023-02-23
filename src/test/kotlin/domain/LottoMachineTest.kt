package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest() {
    @Test
    fun `generator가 (1,2,3,4,5,6)인 리스트를 생성했을 때 로또 번호가(1,2,3,4,5,6)인 로또가 생성된다`() {
        // when
        val actual = LottoMachine(TestLottoNumbersGenerator()).generateLotto().numbers
        // then
        val expected = Lotto(
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )
        ).numbers

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `count가 14이면, 자동 로또가 14개 생성된다`() {
        // when
        val actual = LottoMachine(TestLottoNumbersGenerator()).generateLotteries(Count(14)).size
        // then
        val expected = 14

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `generator가 (1,2,3,4,5,6)인 생성할 때, 모든 로또는 (1,2,3,4,5,6)이다`() {
        // given
        val lotteries = LottoMachine(TestLottoNumbersGenerator()).generateLotteries(Count(14))
        // then
        val expected = Lotto(
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )
        )
        lotteries.forEach { lotto ->
            assertThat((lotto.numbers)).isEqualTo(expected)
        }
    }

    class TestLottoNumbersGenerator() : LottoGeneratorInterface {
        override fun generateLotto(): Lotto {
            return Lotto(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                )
            )
        }
    }
}
