package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ProfitStatusTest {
    @ParameterizedTest
    @MethodSource("profitRateCasesForProfitStatusTest")
    fun `1을 기준으로 수익률에 따라 손해, 이득, 본전 여부를 반환한다`(
        profitRate: Float,
        expectedProfitStatus: ProfitStatus,
    ) {
        val actualProfitStatus = ProfitStatus.from(profitRate)
        assertEquals(expectedProfitStatus, actualProfitStatus)
    }

    companion object {
        @JvmStatic
        fun profitRateCasesForProfitStatusTest(): Stream<Arguments> =
            Stream.of(
                Arguments.of(1.1f, ProfitStatus.PROFIT),
                Arguments.of(0.9f, ProfitStatus.LOSS),
                Arguments.of(1f, ProfitStatus.BREAK_EVEN),
            )
    }
}
