package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MoneyTest {

    @ParameterizedTest
    @ValueSource(longs = [-1, -999])
    fun `구입 금액이 0원 미만이면 예외를 던진다`(value: Long) {
        val exception = assertThrows<IllegalArgumentException> { Money(value) }

        assertThat(exception.message).isEqualTo("0원 이상이어야 합니다.")
    }

    @Test
    fun `입력 금액이 10000원, 물건 가격이 1000원일 때 수량을 10개 반환하는지 테스트`() {
        val result = Money(10000).calculateQuantity(1000)
        assertThat(result).isEqualTo(10)
    }
}
