package domain

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class LottoTest {
    @Test
    fun `서로 다른 1 이상 45 이하의 6개의 숫자로 로또를 생성할 수 있다`() {
        assertDoesNotThrow {
            Lotto.create(listOf(1, 24, 34, 35, 36, 38))
        }
    }

    @Test
    fun `로또를 생성할 때 숫자가 6개가 아니면 에러가 발생한다`() {
        assertThatIllegalArgumentException()
            .isThrownBy { Lotto.create(listOf(1, 24, 34, 35, 37, 45, 22)) }
            .withMessage("로또 번호의 개수는 ${Lotto.NUMBER_SIZE}개여야 합니다. \n입력된 로또 번호 개수: 7")
    }

    @Test
    fun `로또를 생성할 때 6개의 숫자 중 중복이 있으면 에러가 발생한다`() {
        val numbers = listOf(1, 1, 2, 3, 4, 5)

        assertThatIllegalArgumentException()
            .isThrownBy { Lotto.create(numbers) }
            .withMessage("로또 번호는 중복되어선 안됩니다. \n입력된 로또 번호: $numbers")
    }

    @Test
    fun `로또를 생성할 때 로또 번호가 오름차순이 아니라면 에러가 발생한다`() {
        val numbers = listOf(1, 2, 3, 4, 6, 5)

        assertThatIllegalArgumentException()
            .isThrownBy { Lotto.create(numbers) }
            .withMessage("로또 번호는 정렬되어야 합니다.")
    }
}
