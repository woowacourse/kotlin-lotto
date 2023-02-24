package domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CountTest {

    @ParameterizedTest
    @ValueSource(ints = [-1, 101])
    fun `0 이상 100 이하가 아닌 값으로 개수를 생성하면 에러가 발생한다`(value: Int) {
        assertThatIllegalArgumentException().isThrownBy { Count(value) }
            .withMessage("이 프로그램 내에서 개수는 0 이상 100 이하여야 합니다.")
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 100])
    fun `0 이상 100 이하의 값으로 개수를 생성할 수 있다`(value: Int) {
        assertDoesNotThrow { Count(value) }
    }
}
