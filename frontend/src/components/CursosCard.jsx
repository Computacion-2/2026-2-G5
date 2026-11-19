import { Link } from 'react-router';

function CursosCard({ curso, destacado = false, onSeleccionar }) {
  const { id, nombre, creditos, departamento, profesor, profesorNombre, profesorApellido } = curso;
  const nombreDocente = [profesorNombre, profesorApellido].filter(Boolean).join(' ') || profesor;

  return (
    <div className={`card ${destacado ? 'card-destacado' : ''}`}>
      <div className="card-top-row">
        <h3>{nombre}</h3>
        {destacado && <span className="badge-troncal">Troncal</span>}
      </div>
      <p className="card-subtitle">
        <strong>Creditos:</strong> {creditos} · <strong>Depto:</strong> {departamento}
      </p>
      <p className="card-profesor">
        <strong>Docente:</strong> {nombreDocente || 'Sin profesor asignado'}
      </p>
      <div className="card-actions">
        <button
          className="btn btn-primary"
          onClick={() => onSeleccionar && onSeleccionar(nombre)}
        >
          Seleccionar
        </button>
        <Link to={`/cursos/${id}`} className="btn btn-link">
          Ver Detalle
        </Link>
      </div>
    </div>
  );
}

export default CursosCard;
