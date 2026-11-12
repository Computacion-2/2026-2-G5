
function CursosCard ({curso, destacado = false, onSeleccionar}) {
    const {nombre, creditos, departamento, profesor} = curso

    return (
        <div className={`card ${destacado ? 'card-destacado': ''}`}>
            <div className="card-top-row">
                <h3>{nombre}</h3>
                {destacado && <span className="badge-troncal">Troncal</span>}
            </div>
            <p className="card-subtitle">
                <strong> Creditos: </strong>{creditos} . <strong>Depto: </strong> {departamento}
            </p>
            <p className="card-profesor">
                <strong> Docente: </strong>{profesor}
            </p>
            <div className="card-actions">
                <button className="btn btn-primary" onClick={()=> onSeleccionar && onSeleccionar(nombre)}>Seleccionar</button>
            </div>
        </div>
    );
}

export default CursosCard