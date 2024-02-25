package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class LottoMachineTest {
    val fixedNumbers = (1..10).toList()
    val fixedLottoNumbersGenerator = FixedLottoNumbersGenerator(fixedNumbers)

    companion object {
        @JvmStatic
        fun provideLottoPricesAndInvalidPrices() =
            listOf(
                arrayOf(5000, arrayOf(-1, 0, 4999)),
                arrayOf(1000, arrayOf(-1, 0, 999)),
            )
    }

    @ParameterizedTest
    @MethodSource("provideLottoPricesAndInvalidPrices")
    fun `구입 금액은 로또 1장의 가격 이상이여야합니다`(
        lottoPrice: Int,
        invalidBuyPrices: Array<Int>,
    ) {
        invalidBuyPrices.forEach { price ->
            assertThrows<IllegalArgumentException> {
                LottoMachine.createLottos(price, fixedLottoNumbersGenerator, lottoPrice)
            }
        }
    }

    @Test
    fun `사전에 정의된 범위로 로또를 생성한다`() {
        val buyedLotto = LottoMachine.createLottos(Lotto.LOTTO_PRICE, fixedLottoNumbersGenerator).first()
        val expectedLotto = Lotto.of(1, 2, 3, 4, 5, 6)
        assertThat(buyedLotto).isEqualTo(expectedLotto)
    }

    @Test
    fun `로또 한장의 가격이 5,000원일 때 2만원을 투입하면 총 4장의 로또를 발급한다`() {
        val buyedLottos = LottoMachine.createLottos(20000, fixedLottoNumbersGenerator, 5000)
        assertThat(buyedLottos.size).isEqualTo(4)
    }

    @Test
    fun `로또 한장의 가격이 1,000원일 때 1만원을 투입하면 총 10장의 로또를 발급한다`() {
        val buyedLottos = LottoMachine.createLottos(10000, fixedLottoNumbersGenerator, 1000)
        assertThat(buyedLottos.size).isEqualTo(10)
    }
}
