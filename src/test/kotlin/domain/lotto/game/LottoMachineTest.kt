package domain.lotto.game

import domain.game.LottoMachine
import domain.lotto.Lotto
import domain.lotto.PurchasedLotto
import domain.lotto.number.LottoNumber
import domain.money.Money
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class LottoMachineTest {
    private lateinit var lottoMachine: LottoMachine

    @BeforeEach
    fun setUp() {
        lottoMachine = LottoMachine()
    }

    @Test
    fun `Lotto list가 주어졌을 때, PurchasedLotto list가 반환된다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) })
        val purchasedLotto = lottoMachine.purchaseManualLottos(listOf(lotto))
        assertTrue(purchasedLotto is List<PurchasedLotto>)
    }

    @ParameterizedTest
    @CsvSource(value = ["10000,10", "100,0", "12500, 12", "0,0"])
    fun `0이상의 구매금액이 주어졌을 때, 구매금액을 1000으로 나눈 몫만큼 로또를 반환한다`(money: Int, expected: Int) {
        val actual = lottoMachine.purchaseAutoLottos(Money(money)).size
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, -1000, -20000])
    fun `0미만의 구매금액이 주어졌을 때, IllegalArgumentException이 발생한다`(money: Int) {
        assertThrows<IllegalArgumentException> {
            lottoMachine.purchaseAutoLottos(Money(money))
        }
    }
}
