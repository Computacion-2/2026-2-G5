import { useEffect, useState } from 'react'
import './App.css'
import CursosGrid from './components/CursosGrid'
import Navbar from './components/Navbar'
import { MOCKDATA } from './data/MockData'
import CursosFiltro from './components/CursosFiltro'
import useCursos from './hooks/useCursos'

function App() {
  const { cursos, isLoading } = useCursos();

  const [cursoSeleccionado, setCursoSeleccionado] = useState(null);
  const [filtro, setFiltro] = useState('');

  const cursosFiltrados = cursos.filter((c) =>
    c.nombre.toLowerCase().includes(filtro.toLocaleLowerCase()) ||
    c.departamento.toLowerCase().includes(filtro.toLocaleLowerCase()));

  return (
    <div>
      <Navbar />
      <main className='container'>
        <h1>Catalogo de cursos</h1>

        {cursoSeleccionado && (
          <div className='status-box status-loading banner-seleccion'>
            <span>
              Asignatura seleccionada para seguimiento: <strong>{cursoSeleccionado}</strong>
            </span>
            <button className='btn-close-banner' onClick={() => setCursoSeleccionado(null)}>Cerrar</button>
          </div>
        )}

        {isLoading && (
          <div className='status-box-loading'>
            Cargando cursos desde el servidor...
          </div>
        )}

        {!isLoading && (
          <>
          <CursosFiltro filtro={filtro} onFiltroChange={setFiltro} />
          <CursosGrid cursos={cursosFiltrados} onSeleccionarCurso={(nombre) => setCursoSeleccionado(nombre)} />
          </>
          )}
      </main>
    </div>
  )
}

export default App
