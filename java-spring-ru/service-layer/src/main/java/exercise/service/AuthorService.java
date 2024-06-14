package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDTO> getAll() {

        return authorRepository
                .findAll()
                .stream()
                .map(authorMapper::map)
                .toList();
    }

    public AuthorDTO findById(final long id) {

        return authorRepository
                .findById(id)
                .map(authorMapper::map)
                .orElseThrow(() -> new ResourceNotFoundException("Author " + id + " not found"));
    }

    public AuthorDTO create(final AuthorCreateDTO authorCreateDTO) {

        Author author = authorMapper.map(authorCreateDTO);

        return authorMapper.map(authorRepository.save(author));
    }

    public AuthorDTO update(final AuthorUpdateDTO authorUpdateDTO, final long id) {

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author " + id + " not found"));
        authorMapper.update(authorUpdateDTO, author);

        return authorMapper.map(authorRepository.save(author));
    }

    public void delete(final long id) {

        authorRepository.findById(id).ifPresent(authorRepository::delete);
    }
    // END
}
