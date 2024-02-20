package model

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class NumberMatchCounterTest {
    @Test
    fun `당첨 번호와 내가 가진 로또 번호가 몇 개 겹치는지 센다`() {
        val winningNumbers = LottoNumbers(listOf(1,2,3,4,5,6))
        val userNumbers = LottoNumbers(listOf(1,2,3,4,5,6))
        val actual = NumberMatchCounter(winningNumbers, userNumbers)
        assertThat(actual.countMatchNumber()).isEqualTo(6)
    }
}