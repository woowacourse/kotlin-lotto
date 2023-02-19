import domain.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoGameTest {

    /**
     * 1번째 테스트
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
     /**
      * 2번째 테스트
     * - 생성된 로또 리스트 {
     * Lotto(1, 3, 5, 7, 9, 11),
     * Lotto(3, 5, 7, 9, 15, 25),
     * Lotto(3, 5, 7, 34, 42, 45),
     * Lotto(4, 5, 6, 7, 8, 9)
     * }
     * - 당첨 로또와 번호 {
     * Lotto(3, 5, 7, 9, 11, 25), LottoNumber(1)
     * }
     * - 결과
     * Map(Rank.SECOND to 1, Rank.THIRD to 1, Rank.FIFTH to 2)
     */
    @MethodSource("produceLottos")
    @ParameterizedTest
    fun `생성된 로또 리스트 들의 당첨된 로또와 비교하였을 때 rank가 어떻게 이루어졌는지 확인`(
        lottos: List<Lotto>,
        winningLotto: WinningLotto,
        result: Map<Rank, Int>
    ) {
        val lottoGame = LottoGame(lottos, winningLotto)
        val actual = lottoGame.matchGame()
        assertThat(actual).isEqualTo(result)
    }


    companion object {

        @JvmStatic
        fun produceLottos(): List<Arguments> {
            return listOf(
                Arguments.of(
                    listOf(
                        Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.from(it) }),
                        Lotto(listOf(1, 10, 20, 25, 30, 40).map { LottoNumber.from(it) }),
                        Lotto(listOf(3, 5, 15, 34, 42, 45).map { LottoNumber.from(it) }),
                        Lotto(listOf(4, 5, 6, 7, 8, 9).map { LottoNumber.from(it) })
                    ),
                    WinningLotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.from(it) }, LottoNumber.from(7)),
                    mapOf(Rank.FIRST to 1, Rank.FIFTH to 1)
                ),
                Arguments.of(
                    listOf(
                        Lotto(listOf(1, 3, 5, 7, 9, 11).map { LottoNumber.from(it) }),
                        Lotto(listOf(3, 5, 7, 9, 15, 25).map { LottoNumber.from(it) }),
                        Lotto(listOf(3, 5, 7, 34, 42, 45).map { LottoNumber.from(it) }),
                        Lotto(listOf(4, 5, 6, 7, 8, 9).map { LottoNumber.from(it) })
                    ),
                    WinningLotto(listOf(3, 5, 7, 9, 11, 25).map { LottoNumber.from(it) }, LottoNumber.from(1)),
                    mapOf(Rank.SECOND to 1, Rank.THIRD to 1, Rank.FIFTH to 2)
                )
            )
        }
    }
}