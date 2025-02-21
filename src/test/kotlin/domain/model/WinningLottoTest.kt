package domain.model

import domain.service.FakeLottoNumberGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WinningLottoTest {
    @Test
    fun `n장의 Lotto에 대한 당첨 결과 구하기`() {
        val fakeLottoGenerator =
            FakeLottoNumberGenerator(
                listOf(
                    createLotto(1, 2, 3, 4, 5, 6),
                    createLotto(1, 2, 3, 4, 5, 7),
                ),
            )

        val purchaseLotto = fakeLottoGenerator.generate(PurchasePrice(2000))

        val winningLotto = createWinningLotto(1, 3, 4, 5, 6, 7, bonus = 2)
        val result = winningLotto.calculate(purchaseLotto)

        assertThat(result[Rank.SECOND]).isEqualTo(2)
        assertThat(result[Rank.FOURTH]).isEqualTo(0)
        assertThat(result[Rank.MISS]).isEqualTo(0)
    }

    @Test
    fun `로또 번호와 보너스 번호가 중복되면 예외가 발생한다`() {
        val lotto = createLotto(11, 22, 33, 44, 45, 7)

        assertThrows<IllegalArgumentException> {
            WinningLotto(lotto, 7)
        }.apply { assertThat(this).hasMessage("[ERROR] 보너스 번호와 로또 번호는 중복될 수 없습니다.") }
    }

    @ValueSource(ints = [0, 46])
    @ParameterizedTest
    fun `보너스볼 번호는 1이상 45 이하여야한다`(value: Int) {
        assertThrows<IllegalArgumentException> {
            createWinningLotto(10, 20, 30, 40, 44, 45, bonus = value)
        }.apply { assertThat(this).hasMessage("[ERROR] 로또 번호는 1부터 45 사이입니다.") }
    }

    @ValueSource(strings = ["1,2,3,4,5,6"])
    @ParameterizedTest
    fun `Lotto 당첨 결과에 따른 수익률 테스트`(value: String) {
        val values = value.split(",").map { LottoNumber(it.toInt()) }

        val rank = mapOf(Rank.FOURTH to 1)
        val profitRate =
            WinningLotto(Lotto(values), 10).getProfitRate(PurchasePrice(11_000_0), rank)

        assertThat(profitRate).isEqualTo("0.45")
    }

    private fun createLotto(vararg numbers: Int): Lotto {
        return Lotto(numbers.map { LottoNumber(it) })
    }

    private fun createWinningLotto(
        vararg numbers: Int,
        bonus: Int,
    ): WinningLotto {
        return WinningLotto(createLotto(*numbers), bonus)
    }
}
