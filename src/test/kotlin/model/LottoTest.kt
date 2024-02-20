package model

import model.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoTest {
    @ParameterizedTest
    @ValueSource(ints = [1000, 5000, 14000, 35000])
    fun `구입 금액 만큼 로또를 산다 (개수)`(purchased: Int) {
        val actual = Lotto(14000)
        assertThat(actual.getLottoCount()).isEqualTo(14)
    }


}