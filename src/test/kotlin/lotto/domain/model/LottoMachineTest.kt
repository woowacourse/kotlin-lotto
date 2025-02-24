package lotto.domain.model

import lotto.domain.service.FixedLottoNumbersGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class LottoMachineTest {
    @CsvSource(
        "1000, 1",
        "10000, 10",
        "14000, 14",
    )
    @ParameterizedTest(name = "{0}원으로, {1}개를 구매할 수 있다")
    fun `구매 금액으로 구매할 로또 개수를 계산할 수 있다`(
        amount: Int,
        count: Int,
    ) {
        val lottoBundle = LottoMachine().generateLottoBundle(amount)

        assertThat(lottoBundle.lottos.size).isEqualTo(count)
    }

    @Test
    fun `생성된 로또 번호는 정렬되어야 한다`() {
        val numbers = listOf(2, 1, 5, 4, 6, 3)
        val actual = listOf(1, 2, 3, 4, 5, 6)

        val numberGenerator = FixedLottoNumbersGenerator(numbers)
        val lotto = LottoMachine(generator = numberGenerator).generateLottoBundle(1000).lottos.first()

        assertThat(lotto.numbers.map { it.number }).isEqualTo(actual)
    }

    @ValueSource(ints = [999, 0, -1000, -999])
    @ParameterizedTest
    fun `구매 금액이 1000원 미만이라면 예외가 발생한다`(purchaseAmount: Int) {
        assertThrows<IllegalArgumentException> {
            LottoMachine().generateLottoBundle(purchaseAmount)
        }
    }
}
