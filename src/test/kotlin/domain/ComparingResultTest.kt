package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
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

    @ValueSource(ints = [-1, 7, 8])
    @ParameterizedTest
    fun `맞춘 개수가 0~6이 아니라면 예외처리`(matchedCount: Int) {
        assertThatIllegalArgumentException()
            .isThrownBy { ComparingResult(matchedCount, false) }
            .withMessageContaining("[Error] 잘못된 값 : $matchedCount matchedCount는 0 부터 6 까지 가능합니다.")
    }

    @Test
    fun `당첨번호 6개와 같을 때 보너스 번호가 일치할 수 없다`() {
        assertThatIllegalArgumentException()
            .isThrownBy { ComparingResult(6, true) }
            .withMessageContaining("[Error] 당첨번호 6개가 같다면 보너스 번호가 일치할 수 없습니다.")
    }
}
