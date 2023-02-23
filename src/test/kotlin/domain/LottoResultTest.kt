package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class LottoResultTest() {
    @Test
    fun `로또 결과 중 0이 아니라 널 값이 있다면 에러 발생`() {
        val result = Rank.values().filterNot { it == Rank.SECOND }.associateWith { 0 }.toMutableMap()
        assertThrows<IllegalArgumentException> { LottoResult(result) }
    }

    @Test
    fun `로또 결과 중 모든 등수에 대해 0이상의 집계 결과가 있다면 에러 발생하지 않음`() {
        val result = Rank.values().associateWith { 0 }.toMutableMap()
        assertDoesNotThrow { LottoResult(result) }
    }
}
