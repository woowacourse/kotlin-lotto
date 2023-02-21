import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoCountTest {

    @Test
    fun `총 로또 수가 0개라면, 예외를 발생한다`() {
        val exception = assertThrows<IllegalArgumentException> { LottoCount(Count(0), Count(0)) }
        assertEquals(LottoCount.TOTAL_LOTTO_COUNT_ERROR_MESSAGE, exception.message)
    }

    @Test
    fun `수동 로또 수가 총 로또 수보다 클 수가 없다`() {
        val exception = assertThrows<IllegalArgumentException> { LottoCount(Count(2), Count(1)) }
        assertEquals(LottoCount.MANUAL_LOTTO_COUNT_ERROR_MESSAGE, exception.message)
    }

    @Test
    fun `총 로또 수가 5개이고 수동 로또수가 1개라면, 자동 로또 수는 4개이다`() {
        // given
        val totalCount = Count(5)
        val manualCount = Count(1)

        // when
        val actual = LottoCount(manualCount, totalCount).automaticCount.value

        // then
        val expected = 4
        assertEquals(expected, actual)
    }
}
