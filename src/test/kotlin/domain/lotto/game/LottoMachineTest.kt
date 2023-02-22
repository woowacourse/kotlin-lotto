package domain.lotto.game

import domain.game.LottoMachine
import domain.money.Money
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
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

    @ParameterizedTest
    @CsvSource(value = ["10000,10", "100,0", "12500, 12", "0,0"])
    fun `0이상의 구매금액이 주어졌을 때, purchaseAutoLotto 호출시, 구매금액을 1000으로 나눈 몫만큼 로또를 반환한다`(money: Int, expected: Int) {
        val actual = lottoMachine.purchaseAutoLottos(Money(money)).size
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, -1000, -20000])
    fun `0미만의 구매금액이 주어졌을 때, purchaseAutoLotto 호출시, IllegalArgumentException이 발생한다`(money: Int) {
        assertThrows<IllegalArgumentException> {
            lottoMachine.purchaseAutoLottos(Money(money))
        }
    }
}
