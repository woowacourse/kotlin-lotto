package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ManualLottoGeneratorTest {
    @Test
    fun `사용자가 원하는 번호로 로또를 살 수 있다`() {
        val wantedNumbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)
        val generator = ManualLottoGenerator(wantedNumbers)
        val lotto: Lotto? = generator.generate().firstOrNull()
        assertThat(lotto?.numbers).isEqualTo(Lotto(wantedNumbers).numbers)
    }
}
