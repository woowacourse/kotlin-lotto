package domain.lotto

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WinningLottoTest {
    @ParameterizedTest
    @MethodSource("provideWinningLottoAndPurchasedLottoAndMatchedResult")
    fun `구매한 로또 한 개가 주어졌을 때, 호출 시, 일치하는 번호 개수와 보너스 번호 일치 여부를 반환한다`(
        winningLotto: WinningLotto,
        bonusNumber: BonusNumber,
        purchasedLotto: PurchasedLotto,
        matchedResult: Pair<Int, Boolean>
    ) {
        val expected = winningLotto.matchLotto(purchasedLotto, bonusNumber)
        Assertions.assertThat(expected).isEqualTo(matchedResult)
    }

    @ParameterizedTest
    @MethodSource("provideWinningLottoAndNotContainedBonusNumber")
    fun `당첨번호와 6개의 당첨번호에 포함되지 않는 보너스 번호가 주어졌을 때, LottoTicket 객체 생성시, 예외가 발생하지 않는다`(
        lottoNumbers: List<LottoNumber>,
        bonusNumber: BonusNumber
    ) {
        assertDoesNotThrow {
            WinningLotto(lottoNumbers, bonusNumber)
        }
    }

    @ParameterizedTest
    @MethodSource("provideWinningLottoAndContainedBonusNumber")
    fun `당첨번호와 6개의 당첨번호에 포함되는 보너스 번호가 주어졌을 때, WinningLotto 객체 생성시, IllegalStateException이 발생한다`(
        lottoNumbers: List<LottoNumber>,
        bonusNumber: BonusNumber
    ) {
        assertThrows<IllegalStateException> {
            WinningLotto(lottoNumbers, bonusNumber)
        }
    }

    companion object {
        @JvmStatic
        fun provideWinningLottoAndPurchasedLottoAndMatchedResult(): List<Arguments> =
            listOf(
                Arguments.of(
                    WinningLotto((1..6).map { number -> LottoNumber(number) }, BonusNumber(10)),
                    BonusNumber(10),
                    PurchasedLotto((1..6).map { number -> LottoNumber(number) }),
                    Pair(6, false)
                ),
                Arguments.of(
                    WinningLotto(listOf(2, 4, 6, 8, 12, 14).map { number -> LottoNumber(number) }, BonusNumber(10)),
                    BonusNumber(10),
                    PurchasedLotto(listOf(2, 4, 6, 8, 10, 12).map { number -> LottoNumber(number) }),
                    Pair(5, true)
                ),
                Arguments.of(
                    WinningLotto(listOf(2, 4, 6, 8, 12, 14).map { number -> LottoNumber(number) }, BonusNumber(10)),
                    BonusNumber(10),
                    PurchasedLotto(listOf(2, 4, 6, 8, 12, 45).map { number -> LottoNumber(number) }),
                    Pair(5, false)
                ),
                Arguments.of(
                    WinningLotto(listOf(2, 4, 6, 8, 12, 14).map { number -> LottoNumber(number) }, BonusNumber(10)),
                    BonusNumber(10),
                    PurchasedLotto(listOf(2, 4, 6, 8, 44, 45).map { number -> LottoNumber(number) }),
                    Pair(4, false)
                ),
                Arguments.of(
                    WinningLotto(listOf(2, 4, 6, 8, 12, 14).map { number -> LottoNumber(number) }, BonusNumber(10)),
                    BonusNumber(10),
                    PurchasedLotto(listOf(2, 4, 6, 43, 44, 45).map { number -> LottoNumber(number) }),
                    Pair(3, false)
                ),
                Arguments.of(
                    WinningLotto(listOf(2, 4, 6, 8, 12, 14).map { number -> LottoNumber(number) }, BonusNumber(10)),
                    BonusNumber(10),
                    PurchasedLotto(listOf(2, 4, 42, 43, 44, 45).map { number -> LottoNumber(number) }),
                    Pair(2, false)
                ),
                Arguments.of(
                    WinningLotto(listOf(2, 4, 6, 8, 12, 14).map { number -> LottoNumber(number) }, BonusNumber(10)),
                    BonusNumber(10),
                    PurchasedLotto(listOf(2, 41, 42, 43, 44, 45).map { number -> LottoNumber(number) }),
                    Pair(1, false)
                ),
                Arguments.of(
                    WinningLotto(listOf(2, 4, 6, 8, 12, 14).map { number -> LottoNumber(number) }, BonusNumber(10)),
                    BonusNumber(10),
                    PurchasedLotto(listOf(2, 4, 6, 10, 44, 45).map { number -> LottoNumber(number) }),
                    Pair(3, true)
                ),
            )

        @JvmStatic
        fun provideWinningLottoAndNotContainedBonusNumber(): List<Arguments> =
            listOf(
                Arguments.of((1..6).map { number -> LottoNumber(number) }, BonusNumber(7)),
                Arguments.of(
                    listOf(1, 3, 5, 7, 8, 9).map { number -> LottoNumber(number) },
                    BonusNumber(45)
                ),
                Arguments.of(
                    listOf(2, 4, 6, 8, 10, 12).map { number -> LottoNumber(number) },
                    BonusNumber(3)
                )
            )

        @JvmStatic
        fun provideWinningLottoAndContainedBonusNumber(): List<Arguments> =
            listOf(
                Arguments.of((1..6).map { number -> LottoNumber(number) }, BonusNumber(6)),
                Arguments.of(
                    listOf(1, 3, 5, 7, 8, 9).map { number -> LottoNumber(number) },
                    BonusNumber(8)
                ),
                Arguments.of(
                    listOf(2, 4, 6, 8, 10, 12).map { number -> LottoNumber(number) },
                    BonusNumber(12)
                )
            )
    }
}
