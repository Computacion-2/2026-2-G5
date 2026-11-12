function CursosFiltro({ filtro, onFiltroChange }) {

    return (
        <div className="filtro-container">
            <input
                type="text"
                className="input-search"
                placeholder="Buscar curso"
                value={filtro}
                onChange={(e) => onFiltroChange(e.target.value)}>
            </input>
        </div>
    )
}

export default CursosFiltro;