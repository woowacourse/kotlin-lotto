package domain.lotto

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class WinningLottoTest {

    @ParameterizedTest
    @MethodSource("provideValidRangeLottoNumber")
    fun `1이사 45이하 숫자가 주어졌을 때, 로또 객체 생성시, 예외가 발생하지 않는다`(number: Int) {
        val lottoNumber = LottoNumber(number)
        Assertions.assertThat(lottoNumber.value).isBetween(1, 45)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 46, 100])
    fun `1미만 45초과 숫자가 주어졌을 때, 로또 객체 생성시, IllegalArgumentException이 발생한다`() {
        Assertions.assertThatIllegalArgumentException().isThrownBy {
            val lottoNumber = LottoNumber(0)
            Assertions.assertThat(lottoNumber.value).isBetween(1, 45)
        }
    }

    @ParameterizedTest
    @MethodSource("provideSixLottoNumbersAndNotContainedBonusNumber")
    fun `당첨번호와 6개의 당첨번호에 포함되지 않는 보너스 번호가 주어졌을 때, WinningLotto 객체 생성시, 예외가 발생하지 않는다`(
        winningLottoNumbers: List<LottoNumber>,
        bonusNumber: LottoNumber
    ) {
        assertDoesNotThrow {
            WinningLotto(winningLottoNumbers, bonusNumber)
        }
    }

    @ParameterizedTest
    @MethodSource("provideSixLottoNumbersAndContainedBonusNumber")
    fun `당첨번호와 6개의 당첨번호에 포함되는 보너스 번호가 주어졌을 때, WinningLotto 객체 생성시, IllegalStateException이 발생한다`(
        winningLottoNumbers: List<LottoNumber>,
        bonusNumber: LottoNumber
    ) {
        assertThrows<IllegalStateException> {
            WinningLotto(winningLottoNumbers, bonusNumber)
        }
    }

    companion object {
        @JvmStatic
        fun provideValidRangeLottoNumber(): List<Arguments> =
            (1..45).map { number -> Arguments.of(number) }

        @JvmStatic
        fun provideSixLottoNumbersAndNotContainedBonusNumber(): List<Arguments> =
            listOf(
                Arguments.of((1..6).map { number -> LottoNumber(number) }, LottoNumber(7)),
                Arguments.of(
                    listOf(1, 3, 5, 7, 8, 9).map { number -> LottoNumber(number) },
                    LottoNumber(45)
                ),
                Arguments.of(
                    listOf(2, 4, 6, 8, 10, 12).map { number -> LottoNumber(number) },
                    LottoNumber(3)
                )
            )

        @JvmStatic
        fun provideSixLottoNumbersAndContainedBonusNumber(): List<Arguments> =
            listOf(
                Arguments.of((1..6).map { number -> LottoNumber(number) }, LottoNumber(6)),
                Arguments.of(
                    listOf(1, 3, 5, 7, 8, 9).map { number -> LottoNumber(number) },
                    LottoNumber(8)
                ),
                Arguments.of(
                    listOf(2, 4, 6, 8, 10, 12).map { number -> LottoNumber(number) },
                    LottoNumber(12)
                )
            )
    }
}
