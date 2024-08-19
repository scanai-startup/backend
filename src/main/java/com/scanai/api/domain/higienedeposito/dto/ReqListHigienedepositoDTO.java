package com.scanai.api.domain.higienedeposito.dto;

import jakarta.validation.constraints.NotNull;

public record ReqListHigienedepositoDTO(@NotNull Long fkdeposito) {
}
