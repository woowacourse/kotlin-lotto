import domain.* // ktlint-disable no-wildcard-imports
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoGameTest {

    /**
     * 테스트
     * - 생성된 로또 리스트 {
     * Lotto(1, 2, 3, 4, 5, 6)
     * Lotto(1, 10, 20, 25, 30, 40)
     * Lotto(3, 5, 15, 34, 42, 45)
     * Lotto(4, 5, 6, 7, 8, 9) }
     * - 당첨 로또와 번호 {
     * Lotto(1, 2, 3, 4, 5, 6), LottoNumber(7) }
     * - 결과
     * Map(Rank.FIRST to 1, Rank.FIFTH to 1)
     **/
    @MethodSource("produceLottos")
    @ParameterizedTest
    fun `생성된 로또 리스트 들의 당첨된 로또와 비교하였을 때 1등 한 번, 5등 한 번이 당첨되었는지 확인`(
        lottos: List<Lotto>,
        winningLotto: WinningLotto,
        result: Map<Rank, Int>,
    ) {
        val lottoGame = LottoGame(lottos, winningLotto)
        val actual = lottoGame.matchGame()
        assertThat(actual).isEqualTo(result)
    }

    companion object {

        @JvmStatic
        fun produceLottos(): Arguments {
            return Arguments.of(
                listOf(
                    Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.from(it) }),
                    Lotto(listOf(1, 10, 20, 25, 30, 40).map { LottoNumber.from(it) }),
                    Lotto(listOf(3, 5, 15, 34, 42, 45).map { LottoNumber.from(it) }),
                    Lotto(listOf(4, 5, 6, 7, 8, 9).map { LottoNumber.from(it) }),
                ),
                WinningLotto(Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.from(it) }), LottoNumber.from(7)),
                mapOf(Rank.FIRST to 1, Rank.FIFTH to 1),
            )
        }
    }
}
