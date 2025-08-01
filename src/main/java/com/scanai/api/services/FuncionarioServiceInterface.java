package com.scanai.api.services;

import com.scanai.api.domain.funcionario.dto.ResetPasswordDTO;

public interface FuncionarioServiceInterface {

    void resetPassword(ResetPasswordDTO data);
}
