package domain.lotto.game

import domain.game.LottoMachine
import domain.lotto.number.LottoNumber
import domain.lotto.size.LottoSize
import domain.money.Money
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
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
    fun `0 이상의 Money 객체가 주어졌을 때, purchaseAutoLottos 호출시, 구매 금액을 1000으로 나눈 몫만큼 로또를 반환한다`(money: Money, expected: Int) {
        val actual = lottoMachine.purchaseAutoLottos(money).size
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("provideMoneyAndLottoSizeAndLottoNumbers")
    fun `금액, 구매할 로또 개수, 로또 번호 리스트가 주어졌을 때, purchaseManualLottos 호출시, LottoSize만큼 구매한 로또 리스트를 반환한다`(
        money: Money,
        lottoSize: LottoSize,
        lottoNumbers: List<List<LottoNumber>>,
    ) {
        val (_, purchasedLottos) = lottoMachine.purchaseManualLottos(money, lottoSize, lottoNumbers)
        assertEquals(lottoSize.value, purchasedLottos.size)
    }

    @ParameterizedTest
    @MethodSource("provideMoneyAndLottoSizeAndInvalidSizeOfLottoNumbers")
    fun `구매할 로또 개수와 다른 크기의 로또 번호 리스트가 주어졌을 때, purchaseManualLottos 호출시, IllegalArgumentException이 발생한다`(
        money: Money,
        lottoSize: LottoSize,
        lottoNumbers: List<List<LottoNumber>>,
    ) {
        assertThrows<IllegalArgumentException> {
            lottoMachine.purchaseManualLottos(money, lottoSize, lottoNumbers)
        }
    }

    companion object {
        @JvmStatic
        fun provideMoneyAndDividedByThousandValue(): List<Arguments> = listOf(
            Arguments.of(Money(10000), 10),
            Arguments.of(Money(100), 0),
            Arguments.of(Money(12500), 12),
            Arguments.of(Money(0), 0)
        )

        @JvmStatic
        fun provideMoneyAndLottoSizeAndLottoNumbers(): List<Arguments> = listOf(
            Arguments.of(
                Money(10000),
                LottoSize.from("3"),
                listOf(
                    listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) },
                    listOf(1, 5, 10, 20, 40, 44).map { LottoNumber(it) },
                    listOf(45, 44, 43, 42, 41, 40).map { LottoNumber(it) }
                )
            )
        )

        @JvmStatic
        fun provideMoneyAndLottoSizeAndInvalidSizeOfLottoNumbers(): List<Arguments> = listOf(
            Arguments.of(
                Money(10000),
                LottoSize.from("2"),
                listOf(
                    listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) },
                    listOf(1, 5, 10, 20, 40, 44).map { LottoNumber(it) },
                    listOf(45, 44, 43, 42, 41, 40).map { LottoNumber(it) }
                )
            ),
            Arguments.of(
                Money(10000),
                LottoSize.from("4"),
                listOf(
                    listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) },
                    listOf(1, 5, 10, 20, 40, 44).map { LottoNumber(it) },
                    listOf(45, 44, 43, 42, 41, 40).map { LottoNumber(it) }
                )
            )
        )
    }
}
