package br.org.generation.blogPessoal.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.generation.blogPessoal.blogPessoal.model.UserLogin;
import br.org.generation.blogPessoal.blogPessoal.model.Usuario;
import br.org.generation.blogPessoal.blogPessoal.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

        if (repository.findByUsuario(usuario.getUsuario()).isPresent())
            return Optional.empty();

        usuario.setSenha(criptografarSenha(usuario.getSenha()));

        return Optional.of(repository.save(usuario));
    }
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario) {


        if (repository.findByUsuario(usuario.getUsuario()).isPresent()) {

            usuario.setSenha(criptografarSenha(usuario.getSenha()));

            return Optional.of(repository.save(usuario));

        }

        return Optional.empty();

    }
	public Optional<UserLogin> autenticarUsuario(Optional<UserLogin> userLogin) {

        Optional<Usuario> usuario = repository.findByUsuario(userLogin.get().getUsuario());

        if (usuario.isPresent()) {
            if (compararSenhas(userLogin.get().getSenha(), usuario.get().getSenha())) {

                userLogin.get().setId(usuario.get().getId());
                userLogin.get().setNome(usuario.get().getNome());
                userLogin.get().setFoto(usuario.get().getFoto());
                userLogin.get().setToken(gerarBasicToken(userLogin.get().getUsuario(), userLogin.get().getSenha()));
                userLogin.get().setSenha(usuario.get().getSenha());
                userLogin.get().setTipo(usuario.get().getTipo());


                return userLogin;

            }
        }

        return Optional.empty();

    }
	
	private String criptografarSenha(String senha) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(senha);

    }

    private boolean compararSenhas(String senhaDigitada, String senhaBanco) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.matches(senhaDigitada, senhaBanco);

    }

    private String gerarBasicToken(String usuario, String senha) {

        String token = usuario + ":" + senha;
        byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(tokenBase64);

    }
}