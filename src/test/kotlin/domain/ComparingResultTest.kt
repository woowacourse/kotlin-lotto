package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ComparingResultTest {
    @ValueSource(ints = [6, 5, 4, 3, 2, 1, 0])
    @ParameterizedTest
    fun `보너스 번호와 일치 하지 않을 때 생성 테스트`(matchedCount: Int) {
        val comparingResult = ComparingResult(matchedCount, false)
        assertThat(comparingResult.matchedCount).isEqualTo(matchedCount)
    }

    @ValueSource(ints = [5, 4, 3, 2, 1, 0])
    @ParameterizedTest
    fun `보너스 번호와 일치할 때 생성 테스트`(matchedCount: Int) {
        val comparingResult = ComparingResult(matchedCount, true)
        assertThat(comparingResult.matchedCount).isEqualTo(matchedCount)
    }
}
