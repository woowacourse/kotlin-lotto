package domain.lotto

import domain.lotto.number.LottoNumber
import domain.rank.Rank
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PurchasedLottoTest {

    private lateinit var purchasedLotto: PurchasedLotto

    @BeforeEach
    fun setUp() {
        purchasedLotto = PurchasedLotto((1..6).map { LottoNumber(it) })
    }

    @ParameterizedTest
    @MethodSource("providePurchasedLotto")
    fun `구매한 로또가 주어졌을 때, getSortedLotto 호출시, 정렬된 Lotto를 반환한다`(
        purchasedLotto: PurchasedLotto,
        expectedLotto: PurchasedLotto,
    ) {
        val sortedPurchasedLotto = purchasedLotto.getSortedLotto()
        for (i in sortedPurchasedLotto.indices) {
            assertEquals(sortedPurchasedLotto[i].value, expectedLotto[i].value)
        }
    }

    @ParameterizedTest
    @MethodSource("provideWinningLottoAndBonusNumberAndResultRank")
    fun `WinningLotto와 BonusNumber가 주어졌을 때, matchLotto 호출시, expected와 동일한 Rank를 반환한다`(
        winningLotto: WinningLotto,
        bonusNumber: LottoNumber,
        expected: Rank,
    ) {
        val actual = purchasedLotto.matchLotto(winningLotto, bonusNumber)
        Assertions.assertThat(expected).isEqualTo(actual)
    }

    companion object {
        @JvmStatic
        fun providePurchasedLotto(): List<Arguments> =
            listOf(
                Arguments.of(
                    PurchasedLotto((1..6).map { number -> LottoNumber(number) }),
                    PurchasedLotto((1..6).map { number -> LottoNumber(number) })
                ),
                Arguments.of(
                    PurchasedLotto(listOf(1, 3, 5, 14, 13, 11).map { number -> LottoNumber(number) }),
                    PurchasedLotto(listOf(1, 3, 5, 11, 13, 14).map { number -> LottoNumber(number) })
                ),
                Arguments.of(
                    PurchasedLotto(listOf(45, 44, 43, 42, 41, 40).map { number -> LottoNumber(number) }),
                    PurchasedLotto(listOf(40, 41, 42, 43, 44, 45).map { number -> LottoNumber(number) })
                )
            )

        @JvmStatic
        fun provideWinningLottoAndBonusNumberAndResultRank(): List<Arguments> =
            listOf(
                Arguments.of(
                    WinningLotto(
                        Lotto(listOf(1, 2, 3, 43, 44, 45).map { number -> LottoNumber(number) }),
                        LottoNumber(42)
                    ),
                    LottoNumber(42),
                    Rank.FIFTH_WITH_BONUS
                ),
                Arguments.of(
                    WinningLotto(
                        Lotto(listOf(3, 4, 5, 43, 44, 45).map { number -> LottoNumber(number) }),
                        LottoNumber(6)
                    ),
                    LottoNumber(6),
                    Rank.FIFTH_WITH_BONUS
                ),
                Arguments.of(
                    WinningLotto(
                        Lotto(listOf(4, 3, 2, 43, 44, 45).map { number -> LottoNumber(number) }),
                        LottoNumber(42)
                    ),
                    LottoNumber(42),
                    Rank.FIFTH_WITH_BONUS
                ),
                Arguments.of(
                    WinningLotto(
                        Lotto(listOf(1, 2, 3, 4, 44, 45).map { number -> LottoNumber(number) }),
                        LottoNumber(42)
                    ),
                    LottoNumber(42),
                    Rank.FOURTH_WITH_BONUS
                ),
                Arguments.of(
                    WinningLotto(
                        Lotto(listOf(3, 4, 5, 6, 44, 45).map { number -> LottoNumber(number) }),
                        LottoNumber(1)
                    ),
                    LottoNumber(1),
                    Rank.FOURTH_WITH_BONUS
                ),
                Arguments.of(
                    WinningLotto(
                        Lotto(listOf(4, 3, 2, 1, 44, 45).map { number -> LottoNumber(number) }),
                        LottoNumber(43)
                    ),
                    LottoNumber(43),
                    Rank.FOURTH_WITH_BONUS
                ),
                Arguments.of(
                    WinningLotto(
                        Lotto(listOf(1, 2, 3, 4, 5, 45).map { number -> LottoNumber(number) }),
                        LottoNumber(42)
                    ),
                    LottoNumber(42),
                    Rank.THIRD
                ),
                Arguments.of(
                    WinningLotto(
                        Lotto(listOf(3, 4, 5, 1, 2, 45).map { number -> LottoNumber(number) }),
                        LottoNumber(42)
                    ),
                    LottoNumber(42),
                    Rank.THIRD
                ),
                Arguments.of(
                    WinningLotto(
                        Lotto(listOf(4, 3, 2, 1, 5, 45).map { number -> LottoNumber(number) }),
                        LottoNumber(42)
                    ),
                    LottoNumber(42),
                    Rank.THIRD
                ),
                Arguments.of(
                    WinningLotto(
                        Lotto(listOf(1, 2, 3, 4, 5, 45).map { number -> LottoNumber(number) }),
                        LottoNumber(6)
                    ),
                    LottoNumber(6),
                    Rank.SECOND
                ),
                Arguments.of(
                    WinningLotto(
                        Lotto(listOf(3, 4, 5, 1, 2, 37).map { number -> LottoNumber(number) }),
                        LottoNumber(6)
                    ),
                    LottoNumber(6),
                    Rank.SECOND
                ),
                Arguments.of(
                    WinningLotto(
                        Lotto(listOf(4, 3, 2, 1, 5, 43).map { number -> LottoNumber(number) }),
                        LottoNumber(6)
                    ),
                    LottoNumber(6),
                    Rank.SECOND
                ),
                Arguments.of(
                    WinningLotto(Lotto(listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }), LottoNumber(7)),
                    LottoNumber(7),
                    Rank.FIRST_WITH_BONUS
                ),
                Arguments.of(
                    WinningLotto(Lotto(listOf(6, 5, 4, 3, 2, 1).map { number -> LottoNumber(number) }), LottoNumber(7)),
                    LottoNumber(7),
                    Rank.FIRST_WITH_BONUS
                )
            )
    }
}
