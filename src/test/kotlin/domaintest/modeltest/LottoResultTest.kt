package domaintest.modeltest

import domain.model.LottoResult
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoResultTest {

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `1등인 경우를 확인하기`(matchBonus: Boolean) {
        assertThat(LottoResult.valueOf(6, matchBonus)).isEqualTo(LottoResult.FIRST)
    }

    @Test
    fun `2등인 경우를 확인하기`() {
        assertThat(LottoResult.valueOf(5, true)).isEqualTo(LottoResult.SECOND)
    }

    @Test
    fun `3등인 경우를 확인하기`() {
        assertThat(LottoResult.valueOf(5, false)).isEqualTo(LottoResult.THIRD)
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `4등인 경우를 확인하기`(matchBonus: Boolean) {
        assertThat(LottoResult.valueOf(4, matchBonus)).isEqualTo(LottoResult.FOURTH)
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `5등인 경우를 확인하기`(matchBonus: Boolean) {
        assertThat(LottoResult.valueOf(3, matchBonus)).isEqualTo(LottoResult.FIFTH)
    }
}
