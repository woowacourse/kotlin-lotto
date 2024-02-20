import lotto.model.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGameTest {
    @Test
    fun `보너스 매치`() {
        assertThat(matchBonusNumber(setOf(1,2,3,4,5,6),5)).isTrue()
    }
    @Test
    fun `당첨 계산`() {
        assertThat(matchCount(setOf(1,2,3,4,5,6), setOf(1,2,3,4,5,6))).isEqualTo(6)
    }

    @Test
    fun `당첨금 계산 (보너스 당첨)`(){
        assertThat(findRanking(0,true)).isEqualTo(LottoPrize.BOOM)
        assertThat(findRanking(1,true)).isEqualTo(LottoPrize.BOOM)
        assertThat(findRanking(2,true)).isEqualTo(LottoPrize.BOOM)
        assertThat(findRanking(3,true)).isEqualTo(LottoPrize.FIFTH)
        assertThat(findRanking(4,true)).isEqualTo(LottoPrize.FOURTH)
        assertThat(findRanking(5,true)).isEqualTo(LottoPrize.SECOND)
        assertThat(findRanking(6,true)).isEqualTo(LottoPrize.FIRST)
    }

    @Test
    fun `당첨금 계산 (보너스 미당첨)`(){
        assertThat(findRanking(0,false)).isEqualTo(LottoPrize.BOOM)
        assertThat(findRanking(1,false)).isEqualTo(LottoPrize.BOOM)
        assertThat(findRanking(2,false)).isEqualTo(LottoPrize.BOOM)
        assertThat(findRanking(3,false)).isEqualTo(LottoPrize.FIFTH)
        assertThat(findRanking(4,false)).isEqualTo(LottoPrize.FOURTH)
        assertThat(findRanking(5,false)).isEqualTo(LottoPrize.THIRD)
        assertThat(findRanking(6,false)).isEqualTo(LottoPrize.FIRST)
    }

    @Test
    fun `수익 계산`(){
        assertThat(0).isEqualTo(prizeCalculate(mapOf(LottoPrize.BOOM to 1)))
        assertThat(5_000).isEqualTo(prizeCalculate(mapOf(LottoPrize.FIFTH to 1)))
        assertThat(50_000).isEqualTo(prizeCalculate(mapOf(LottoPrize.FOURTH to 1)))
        assertThat(1_500_000).isEqualTo(prizeCalculate(mapOf(LottoPrize.THIRD to 1)))
        assertThat(30_000_000).isEqualTo(prizeCalculate(mapOf(LottoPrize.SECOND to 1)))
        assertThat(2_000_000_000).isEqualTo(prizeCalculate(mapOf(LottoPrize.FIRST to 1)))
    }
}