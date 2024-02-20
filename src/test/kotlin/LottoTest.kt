import model.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 하나에 들어가는 숫자가 6개가 아닌 경우, 예외를 발생시킨다`() {
        // given
        val nums = intArrayOf(1, 2, 3, 4, 5)
        val nums2 = intArrayOf(1, 2, 3, 4, 5, 6, 7)
        // then
        assertAll(
            { assertThrows<IllegalArgumentException> { Lotto(*nums) } },
            { assertThrows<IllegalArgumentException> { Lotto(*nums2) } },
        )
    }

    @Test
    fun `로또 하나에 들어가는 숫자는 6개다`() {
        val nums = intArrayOf(1, 2, 3, 4, 5, 6)
        assertThat(nums).hasSize(6)
    }

    @Test
    fun `로또 하나에 들어가는 숫자가 중복된다면, 예외를 발생시킨다`() {
        val nums = intArrayOf(1, 1, 2, 3, 4, 5)
        assertThrows<IllegalArgumentException> { Lotto(*nums) }
    }
}
