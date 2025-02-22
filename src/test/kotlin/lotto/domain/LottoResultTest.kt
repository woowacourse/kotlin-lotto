package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoResultTest {
    @ParameterizedTest
    @MethodSource("지난 주 당첨 번호를 참고하여 구매한 로또의 결과를 계산한다 테스트 케이스")
    fun `지난 주 당첨 번호를 참고하여 구매한 로또의 결과를 계산한다`(boughtLotto: Lotto, expectedResult: LottoResult) {
        val winningLotto = WinningLotto(Lotto(1, 2, 3, 4, 5, 6), bonusNumber = LottoNumber(7))
        assertThat(LottoResult.from(winningLotto, boughtLotto)).isEqualTo(expectedResult)
    }

    companion object {
        @JvmStatic
        fun `지난 주 당첨 번호를 참고하여 구매한 로또의 결과를 계산한다 테스트 케이스`() = listOf(
            Arguments.of(Lotto(1, 2, 3, 4, 5, 6), LottoResult.FIRST),
            Arguments.of(Lotto(1, 2, 3, 4, 5, 7), LottoResult.SECOND),
            Arguments.of(Lotto(1, 2, 3, 4, 5, 45), LottoResult.THIRD),
            Arguments.of(Lotto(1, 2, 3, 4, 44, 45), LottoResult.FOURTH),
            Arguments.of(Lotto(1, 2, 3, 43, 44, 45), LottoResult.FIFTH),
            Arguments.of(Lotto(1, 2, 42, 43, 44, 45), LottoResult.FAIL),
            Arguments.of(Lotto(1, 41, 42, 43, 44, 45), LottoResult.FAIL),
            Arguments.of(Lotto(40, 41, 42, 43, 44, 45), LottoResult.FAIL)
        )
    }
}
