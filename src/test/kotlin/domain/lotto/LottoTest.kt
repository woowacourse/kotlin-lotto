package domain.lotto

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {
    @ParameterizedTest
    @MethodSource("provideSixLottoNumbers")
    fun `6개의 로또 번호 리스트가 주어졌을 때, 로또 객체 생성시, 예외 발생하지 않는다`(lottoNumbers: List<LottoNumber>) {
        assertDoesNotThrow {
            Lotto(lottoNumbers)
        }
    }

    @ParameterizedTest
    @MethodSource("provideFiveLottoNumbers")
    fun `로또 번호가 5개 일 때, 로또 객체 생성시, IllegalArgumentException이 발생한다`(lottoNumbers: List<LottoNumber>) {
        assertThrows<IllegalArgumentException> {
            Lotto(lottoNumbers)
        }
    }

    @ParameterizedTest
    @MethodSource("provideDistinctSixLottoNumbers")
    fun `중복되지 않는 로또 번호가 6개 주어졌을 때, 로또 객체 생성시, 예외가 발생하지 않는다`(lottoNumbers: List<LottoNumber>) {
        assertDoesNotThrow {
            Lotto(lottoNumbers)
        }
    }

    @ParameterizedTest
    @MethodSource("provideDuplicatedSixLottoNumbers")
    fun `중복되는 로또 번호가 6개 주어졌을 때, 로또 객체 생성시, IllegalStateException이 발생한다`(lottoNumbers: List<LottoNumber>) {
        assertThrows<IllegalStateException> {
            Lotto(lottoNumbers)
        }
    }

    companion object {
        @JvmStatic
        fun provideSixLottoNumbers(): List<Arguments> =
            listOf(
                Arguments.of((1..6).map { number -> LottoNumber(number) })
            )

        @JvmStatic
        fun provideFiveLottoNumbers(): List<Arguments> =
            listOf(
                Arguments.of((1..5).map { number -> LottoNumber(number) })
            )

        @JvmStatic
        fun provideDistinctSixLottoNumbers(): List<Arguments> =
            listOf(
                Arguments.of((1..6).map { number -> LottoNumber(number) }),
                Arguments.of(listOf(1, 3, 5, 7, 8, 9).map { number -> LottoNumber(number) }),
                Arguments.of(listOf(2, 4, 6, 8, 10, 12).map { number -> LottoNumber(number) })
            )

        @JvmStatic
        fun provideDuplicatedSixLottoNumbers(): List<Arguments> =
            listOf(
                Arguments.of(listOf(1, 1, 2, 3, 4, 5).map { number -> LottoNumber(number) }),
                Arguments.of(listOf(1, 1, 1, 1, 1, 1).map { number -> LottoNumber(number) }),
                Arguments.of(listOf(1, 4, 13, 13, 41, 41).map { number -> LottoNumber(number) })
            )
    }
}
