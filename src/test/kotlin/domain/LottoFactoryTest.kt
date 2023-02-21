package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoFactoryTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 100])
    fun `1개 이상 100개 이하의 개수만큼 로또를 생성할 수 있다`(count: Int) {
        val result = LottoFactory.create(count)

        assertThat(result.size).isEqualTo(count)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 101])
    fun `로또를 생성할 때 생성 개수가 1개 이상 100개 이하가 아니면 에러가 발생한다`(count: Int) {
        assertThatIllegalArgumentException()
            .isThrownBy { LottoFactory.create(count) }
            .withMessage("한 번에 생성할 수 있는 로또 개수는 1개 이상 100개 이하입니다.\n잘못된 값: $count")
    }
}
