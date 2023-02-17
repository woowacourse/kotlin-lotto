package domain.lotto.game

import domain.game.LottoMachine
import domain.money.Money
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoMachineTest {
    private lateinit var lottoMachine: LottoMachine

    @BeforeEach
    fun setUp() {
        lottoMachine = LottoMachine()
    }

    @ParameterizedTest
    @MethodSource("provideMoneyAndDividedByThousandValue")
    fun `0 이상의 Money 객체가 주어졌을 때, purchaseLotto 호출시, 구매 금액을 1000으로 나눈 몫만큼 로또를 반환한다`(money: Money, expected: Int) {
        val actual = lottoMachine.purchaseLottos(money).size
        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun provideMoneyAndDividedByThousandValue(): List<Arguments> = listOf(
            Arguments.of(Money(10000), 10),
            Arguments.of(Money(100), 0),
            Arguments.of(Money(12500), 12),
            Arguments.of(Money(0), 0)
        )
    }
}
