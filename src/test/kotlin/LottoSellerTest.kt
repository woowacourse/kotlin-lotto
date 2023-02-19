package lottogenerator

import domain.LottoSeller
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoSellerTest {

    @ParameterizedTest
    @ValueSource(ints = [1,2,3,4,5,6])
    fun `자동 로또를 여러개 판매하기`(autoMaticLottosCount:Int){
        assertThat(
            LottoSeller().sellAutoMaticLottos(autoMaticLottosCount).size
        ).isEqualTo(autoMaticLottosCount)
    }

}