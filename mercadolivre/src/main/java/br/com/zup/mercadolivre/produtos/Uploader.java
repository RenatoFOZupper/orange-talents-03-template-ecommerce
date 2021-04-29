package br.com.zup.mercadolivre.produtos;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public interface Uploader {

	Set<String> send(List<MultipartFile> imagens);

}
