package domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RankTest {
    private lateinit var resultFirst: Rank
    private lateinit var resultSecond: Rank
    private lateinit var resultThird: Rank
    private lateinit var resultFourthBonusFalse: Rank
    private lateinit var resultFourthBonusTrue: Rank

    @BeforeEach
    fun setUp() {
        resultFirst = Rank.valueOf(6, false)
        resultSecond = Rank.valueOf(5, true)
        resultThird = Rank.valueOf(5, false)
        resultFourthBonusFalse = Rank.valueOf(4, false)
        resultFourthBonusTrue = Rank.valueOf(4, true)
    }

    @Test
    fun `당첨 로또번호가 사용자가 입력한 로또 번호와 6개가 일치하면 1등 순위가 반환된다`() {
        assertThat(resultFirst).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `당첨 로또번호가 사용자가 입력한 로또 번호와 5개가 일치하고 보너스 번호가 일치하면 2등 순위가 반환된다`() {
        assertThat(resultSecond).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `당첨 로또번호가 사용자가 입력한 로또 번호와 5개 일치하고 보너스 번호가 일치하지 않으면 3등 순위가 반환된다`() {
        assertThat(resultThird).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `당첨 로또번호가 사용자가 입력한 로또 번호와 4개가 일치하고 보너스 번호가 일치하지 않으면 4등 순위가 반환된다`() {
        assertThat(resultFourthBonusFalse).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `당첨 로또번호가 사용자가 입력한 로또 번호와 4개가 일치하고 보너스 번호도 일치하면 4등 순위가 반환된다`() {
        assertThat(resultFourthBonusTrue).isEqualTo(Rank.FOURTH)
    }
}
